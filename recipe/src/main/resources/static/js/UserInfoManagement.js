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

//10づつ一覧を取得する
let offset = 0; // 初期値
const limit = 10;
let loading = false; // ロード中フラグ
const screenId = "UserInfoManagement";

//初期表示はControllerから取得してさらに読み込む分はAPIで取得するように後で修正する
function loadRecipes(isInit = false) {
    if (loading) return; // すでにロード中なら処理しない
    loading = true;
	
	//レシピが一件もない場合
	const recipeList = document.getElementById("recipe-list");
	if (!recipeList) return;
	
    const endpoint = isInit ? `/api/recipes/init?screenId=${screenId}` : `/api/recipes/more?offset=${offset}&screenId=${screenId}`;
    fetch(endpoint)
        .then(response => response.json())
        .then(data => {
            data.forEach(recipe => {
                const recipeElement = document.createElement("div");
                recipeElement.className = "recipe-item";
                recipeElement.textContent = recipe.recipe_name;
                document.getElementById("recipe-list").appendChild(recipeElement);
            });

            if (!isInit) offset += limit; // もっと見る用にoffsetを更新
        })
        .finally(() => loading = false); // ロード完了後、フラグを解除
}

// 初期表示
document.addEventListener("DOMContentLoaded", () => loadRecipes(true));

// スクロールイベントで自動読み込み
window.addEventListener("scroll", () => {
    const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
    if (scrollTop + clientHeight >= scrollHeight - 10) {
        loadRecipes();
    }
});