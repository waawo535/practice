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

function saveProfile() {
    // 保存処理はここに実装
    alert('プロフィールが保存されました。');
    showProfileView();
}