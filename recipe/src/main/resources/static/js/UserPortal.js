// UserPortal.js
document.addEventListener('DOMContentLoaded', function() {
    // タブ切り替え
    const tabs = document.querySelectorAll('.tab-button');
    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            // アクティブクラスの切り替え
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');

            // コンテンツの切り替え
            const contents = document.querySelectorAll('.tab-content');
            contents.forEach(content => content.classList.remove('active'));
            document.getElementById(`${tab.dataset.tab}-content`).classList.add('active');
        });
    });
});

// お気に入り切り替え
function toggleFavorite(button) {
    event.stopPropagation(); // カード全体のクリックイベントを防止
    const recipeId = button.getAttribute('data-recipe-id');
    
    // お気に入りボタンの見た目を切り替え
    button.classList.toggle('active');
    
    // バックエンドへのリクエスト
    fetch('/api/favorites/toggle', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            recipeId: recipeId
        })
    })
    .then(response => response.json())
    .then(data => {
        if (!data.success) {
            // エラーの場合は元に戻す
            button.classList.toggle('active');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        // エラーの場合は元に戻す
        button.classList.toggle('active');
    });
}
function viewRecipeDetail(recipeId){
	window.location.href = `/recipe/recipeDetail/initShow?recipeId=${recipeId}`;
}