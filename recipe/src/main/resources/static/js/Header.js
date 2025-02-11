function logout() {
	if (confirm("ログアウトしますか？")) {
		window.location.href = "/recipe/Login/logout";
	}
}

function userPortal(){
	window.location.href = "/recipe/UserPortal/initShow";
}

function userInfo(){
	window.location.href = "/recipe/UserInfoManagement/initShow";
}

function createRecipe(){
	window.location.href = "/recipe/CreateRecipe/initShow";
}