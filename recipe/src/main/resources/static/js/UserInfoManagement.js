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
//function saveProfile() {
////    window.location.href = "/recipe/UserInfoManagement/editUserInfo";
//	fetch('/recipe/UserInfoManagement/editUserInfo', {
//	  method: 'POST',
//	  headers: {
//	    'Content-Type': 'application/json',
//	    // 例: CSRFトークンが必要な場合はここに追加する
//	  },
//	  body: JSON.stringify({ someParam: 'someValue' })
//	})
//	.then(response => {
//	  // 成功時の処理
//	})
//	.catch(err => {
//	  // エラー時の処理
//	});

//}