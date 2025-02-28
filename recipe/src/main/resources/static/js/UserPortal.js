document.addEventListener('DOMContentLoaded', function() {
    // タブ切り替え
    const tabs = document.querySelectorAll('.tab-button');
    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            // アクティブクラスの切り替え
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');

            // コンテンツの切り替え
            const tabType = tab.dataset.tab;
            const contents = document.querySelectorAll('.tab-content');
            contents.forEach(content => content.classList.remove('active'));
            
            // タブのタイプに応じたコンテンツをアクティブにする
            const targetContent = document.getElementById(`${tabType}-content`);
            if (targetContent) {
                targetContent.classList.add('active');
            }
        });
    });
});
// お気に入り切り替え
function toggleFavorite(button) {
    event.stopPropagation(); // カード全体のクリックイベントを防止
    const recipeId = button.getAttribute('data-recipe-id');
    
    // 処理中の状態を視覚的に示す（オプション）
    button.classList.add('processing');
    
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
        // 処理中表示を解除
        button.classList.remove('processing');
        
        if (data.success) {
            // 成功した場合のみボタンの状態を切り替え
            button.classList.toggle('active');
        } else {
            // エラーメッセージを表示（オプション）
            console.error('お気に入り操作に失敗しました');
        }
    })
    .catch(error => {
        // 処理中表示を解除
        button.classList.remove('processing');
        console.error('Error:', error);
    });
}
function viewRecipeDetail(recipeId){
	window.location.href = `/recipe/recipeDetail/initShow?recipeId=${recipeId}`;
}