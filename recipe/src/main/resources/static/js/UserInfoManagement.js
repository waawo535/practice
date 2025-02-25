// プロフィール表示・編集切り替え
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

// 画面遷移関数
function createRecipe(){
    window.location.href = "/recipe/CreateRecipe/initShow";
}

function userPortal(){
    window.location.href = "/recipe/UserPortal/initShow";
}

// レシピ削除
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

// お気に入り切り替え
function toggleFavorite(button) {
    event.stopPropagation(); // イベントの伝播を停止
    const recipeId = button.getAttribute('data-recipe-id');
    
    // バックエンドへのリクエスト
    fetch('/recipe/api/recipe/favorites', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            recipeId: recipeId
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            // お気に入りタブ内で操作した場合は、そのアイテムを削除
            if (document.getElementById('favorite-recipes-content').classList.contains('active')) {
                const recipeItem = button.closest('.recipe-item');
                if (recipeItem) {
                    recipeItem.style.opacity = '0';
                    setTimeout(() => {
                        recipeItem.remove();
                        
                        // お気に入りが0件になった場合、no-contentを表示
                        const favoriteList = document.getElementById('favorite-recipe-list');
                        if (favoriteList && favoriteList.children.length === 0) {
                            const noContentDiv = document.createElement('div');
                            noContentDiv.className = 'no-content';
                            noContentDiv.innerHTML = `
                                <i class="fas fa-heart"></i>
                                <p>お気に入りレシピがありません</p>
                                <p class="sub-text">気に入ったレシピを見つけてお気に入りに追加しましょう！</p>
                                <button class="browse-btn" onclick="userPortal()">レシピを探す</button>
                            `;
                            document.getElementById('favorite-recipes-content').innerHTML = '';
                            document.getElementById('favorite-recipes-content').appendChild(noContentDiv);
                        }
                    }, 300);
                }
            }
        } else {
            // エラーメッセージ表示
            alert('お気に入り操作に失敗しました。もう一度お試しください。');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('エラーが発生しました。もう一度お試しください。');
    });
}

// タブ切り替え
document.addEventListener('DOMContentLoaded', function() {
    // タブ切り替え
    const tabs = document.querySelectorAll('.tab-button');
    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            // アクティブクラスの切り替え
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');

            // 現在のタブを更新
            currentTab = tab.dataset.tab;

            // コンテンツの切り替え
            const contents = document.querySelectorAll('.tab-content');
            contents.forEach(content => content.classList.remove('active'));
            document.getElementById(`${tab.dataset.tab}-content`).classList.add('active');
        });
    });
});

// 無限スクロール関連
let offset = 10; // 初期値
const limit = 10;
let loading = false; // ロード中フラグ
let currentTab = "my-recipes"; // 現在のタブ

// 初期表示はControllerから取得してさらに読み込む分はAPIで取得する
function loadMoreRecipes() {
    if (loading) return; // すでにロード中なら処理しない
    loading = true;
    
    const endpoint = `/recipe/api/recipes/more?offset=${offset}&screenId=UserInfoManagement&tab=${currentTab}`;
    fetch(endpoint)
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                // これ以上読み込むデータがない場合
                window.removeEventListener("scroll", checkScroll);
                return;
            }
            
            // データに基づいて表示するリストを決定
            const targetList = currentTab === "my-recipes" ? 
                document.getElementById("recipe-list") : 
                document.getElementById("favorite-recipe-list");
            
            if (!targetList) return;
            
            data.forEach(recipe => {
                const recipeElement = document.createElement("li");
                recipeElement.className = "recipe-item";
                recipeElement.setAttribute("data-recipe-id", recipe.recipeId);
                recipeElement.setAttribute("onclick", `event.stopPropagation(); viewRecipeDetail('${recipe.recipeId}');`);

                // レシピ画像の生成
                const imgDiv = document.createElement("div");
                imgDiv.className = "recipe-img";
                const img = document.createElement("img");
                img.className = "recipe-image";
                img.src = recipe.recipeImg ? `/recipe/uploads/${recipe.recipeImg}` : "/recipe/img/noimage.jpg";
                img.alt = "レシピ画像";
                imgDiv.appendChild(img);
                recipeElement.appendChild(imgDiv);

                // レシピ情報の生成
                const infoDiv = document.createElement("div");
                infoDiv.className = "recipe-info";

                const title = document.createElement("h3");
                title.textContent = recipe.recipeName || "レシピ名";
                infoDiv.appendChild(title);

                const description = document.createElement("p");
                description.textContent = recipe.recipeDescrip || "レシピ説明文";
                infoDiv.appendChild(description);

                if (recipe.recipeAveRating && recipe.recipeAveRating !== 0) {
                    const rating = document.createElement("p");
                    rating.className = "rating";
                    rating.textContent = `評価: ${recipe.recipeAveRating}`;
                    infoDiv.appendChild(rating);
                }

                recipeElement.appendChild(infoDiv);

                // アクション部分 (タブによって異なる)
                const actionsDiv = document.createElement("div");
                actionsDiv.className = "recipe-actions";

                if (currentTab === "my-recipes") {
                    // 自分のレシピの場合は編集と削除ボタン
                    const editForm = document.createElement("form");
                    editForm.action = "/recipe/editRecipe/initShow";
                    editForm.method = "GET";
                    const inputEdit = document.createElement("input");
                    inputEdit.type = "hidden";
                    inputEdit.name = "recipeId";
                    inputEdit.value = recipe.recipeId;
                    const editButton = document.createElement("button");
                    editButton.type = "submit";
                    editButton.textContent = "編集";
                    editForm.appendChild(inputEdit);
                    editForm.appendChild(editButton);
                    actionsDiv.appendChild(editForm);

                    const deleteButton = document.createElement("button");
                    deleteButton.className = "delete-btn";
                    deleteButton.textContent = "削除";
                    deleteButton.setAttribute("data-recipe-id", recipe.recipeId);
                    deleteButton.setAttribute("onclick", `event.stopPropagation(); deleteRecipe('${recipe.recipeId}');`);
                    actionsDiv.appendChild(deleteButton);
                } else {
                    // お気に入りの場合はお気に入り解除ボタン
                    const favoriteButton = document.createElement("button");
                    favoriteButton.className = "favorite-toggle-btn active";
                    favoriteButton.setAttribute("data-recipe-id", recipe.recipeId);
                    favoriteButton.setAttribute("onclick", `event.stopPropagation(); toggleFavorite(this);`);
                    favoriteButton.innerHTML = '<i class="fas fa-heart"></i> お気に入り解除';
                    actionsDiv.appendChild(favoriteButton);
                }

                recipeElement.appendChild(actionsDiv);
                targetList.appendChild(recipeElement);
            });

            offset += limit; // オフセットを更新
            loading = false;
        })
        .catch(error => {
            console.error("レシピ読み込みエラー:", error);
            loading = false;
        });
}

// スクロール検知関数
function checkScroll() {
    const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
    if (scrollTop + clientHeight >= scrollHeight - 10) {
        loadMoreRecipes();
    }
}

// スクロールイベント
window.addEventListener("scroll", checkScroll);

// レシピ詳細画面へ遷移
function viewRecipeDetail(recipeId) {
    window.location.href = `/recipe/recipeDetail/initShow?recipeId=${recipeId}`;
}