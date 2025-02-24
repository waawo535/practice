function showEditProfile() {
    document.getElementById('profileViewInf').classList.remove('active');
	document.getElementById('profileViewRec').classList.remove('active');
    document.getElementById('profileEdit').classList.add('active');
}

function showProfileView() {
    document.getElementById('profileEdit').classList.remove('active');
	document.getElementById('profileViewInf').classList.add('active');
    document.getElementById('profileViewRec').classList.add('active');
}

function createRecipe(){
	window.location.href = "/recipe/CreateRecipe/initShow";
}

function deleteRecipe(recipeId) {
    if (!confirm("本当に削除しますか？")) return;

    fetch(`/recipe/api/recipe/${recipeId}/delete`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("削除に失敗しました");
        }
		alert("削除しました");
        window.location.reload(); 
    })
    .catch(error => console.error("削除エラー:", error));
}


//10づつ一覧を取得する
let offset = 10; // 初期値
const limit = 10;
let loading = false; // ロード中フラグ
const screenId = "UserInfoManagement";

//初期表示はControllerから取得してさらに読み込む分はAPIで取得する
function loadRecipes() {
    if (loading) return; // すでにロード中なら処理しない
    loading = true;
	
	//レシピが一件もない場合
	const recipeList = document.getElementById("recipe-list");
	if (!recipeList) return;
	
    const endpoint = `/recipe/api/recipes/more?offset=${offset}&screenId=${screenId}`;
    fetch(endpoint)
        .then(response => response.json())
        .then(data => {
            data.forEach(recipe => {
				const recipeElement = document.createElement("li"); 
                recipeElement.className = "recipe-item";
                recipeElement.setAttribute("onclick", `viewRecipeDetail(${recipe.recipeId});`);

                // レシピ画像の生成
                if (recipe.recipeImg != null) {
                    const imgDiv = document.createElement("div");
                    imgDiv.className = "recipe-img";
                    const img = document.createElement("img");
                    img.className = "recipe-image";
                    img.src = recipe.recipeImg;
                    img.alt = "レシピ画像";
                    imgDiv.appendChild(img);
                    recipeElement.appendChild(imgDiv);
                }

                // レシピ情報の生成
                const infoDiv = document.createElement("div");
                infoDiv.className = "recipe-info";

                const title = document.createElement("h3");
                title.textContent = recipe.recipeName || "レシピ名";
                infoDiv.appendChild(title);

                const description = document.createElement("p");
                description.textContent = recipe.recipeDescrip || "レシピ説明文";
                infoDiv.appendChild(description);

                const rating = document.createElement("p");
                rating.className = "rating";
                rating.textContent = `評価: ${recipe.recipeAveRating || "未評価"}`;
                infoDiv.appendChild(rating);

                recipeElement.appendChild(infoDiv);

                // 編集・削除ボタン
                const actionsDiv = document.createElement("div");
                actionsDiv.className = "recipe-actions";

                const editForm = document.createElement("form");
                editForm.action = "/editRecipe/initShow";
				editForm.method = "GET";
				const inputEdit = document.createElement("input");
				inputEdit.type = "hidden";
				inputEdit.name = "recipeId";
				inputEdit.value = `${recipe.recipeId}`;
				const editButton = document.createElement("button");
				editButton.type = "submit";
				editButton.textContent = "編集";
				editForm.appendChild(inputEdit);
				editForm.appendChild(editButton);
                actionsDiv.appendChild(editForm);

                const deleteButton = document.createElement("button");
                deleteButton.className = "delete-btn";
                deleteButton.textContent = "削除";
                deleteButton.setAttribute("onclick", `event.stopPropagation(); deleteRecipe(${recipe.recipeId});`);
                actionsDiv.appendChild(deleteButton);

                recipeElement.appendChild(actionsDiv);

                recipeList.appendChild(recipeElement);
            });

            offset += limit; // もっと見る用にoffsetを更新
        })
        .finally(() => loading = false); // ロード完了後、フラグを解除
}

// スクロールイベントで自動読み込み
window.addEventListener("scroll", () => {
    const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
    if (scrollTop + clientHeight >= scrollHeight - 10) {
        loadRecipes();
    }
});

function viewRecipeDetail(recipeId){
	window.location.href = `/recipe/recipeDetail/initShow?recipeId=${recipeId}`;
}