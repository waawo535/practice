function showEditProfile() {
    document.getElementById('profileView').classList.remove('active');
    document.getElementById('profileEdit').classList.add('active');
}

function showProfileView() {
    document.getElementById('profileEdit').classList.remove('active');
    document.getElementById('profileView').classList.add('active');
}

function saveProfile() {
    // 保存処理はここに実装
    alert('プロフィールが保存されました。');
    showProfileView();
}