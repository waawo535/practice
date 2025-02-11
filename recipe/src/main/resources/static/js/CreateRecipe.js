
  const recipeImageInput = document.getElementById('recipeImageInput');
  const imagePreview = document.getElementById('imagePreview');

  recipeImageInput.addEventListener('change', function (e) {
    const file = e.target.files[0];
    if (!file) {
      imagePreview.innerHTML = '';
      return;
    }
    const reader = new FileReader();
    reader.onload = function () {
      const img = document.createElement('img');
      img.src = reader.result;
	  img.style.maxWidth = '50%';
      imagePreview.innerHTML = '';
      imagePreview.appendChild(img);
    };
    reader.readAsDataURL(file);
  });



// ********** 材料の動的追加 **********
const ingredientList = document.getElementById('ingredientList');
const addIngredientBtn = document.getElementById('addIngredientBtn');

function addIngredientItem() {
  const ingredientList = document.getElementById('ingredientList');

  // 既存の要素をコピー
  const firstItem = ingredientList.querySelector('.ingredient-item');
  const newItem = firstItem.cloneNode(true);

  // インデックスを更新
  const index = document.querySelectorAll('.ingredient-item').length;
  const input = newItem.querySelector('input');
  input.name = `recipeIngredients[${index}].ingredientName`;
  input.value = ''; // 新しい行なので値をクリア

  // 新しい要素を追加
  ingredientList.appendChild(newItem);
}

// プラスボタンが押されたら材料を１行追加
addIngredientBtn.addEventListener('click', () => {
  addIngredientItem();
});

// ********** 作り方の動的追加 **********
const stepsList = document.getElementById('stepsList');
const addStepBtn = document.getElementById('addStepBtn');


function addStepItem() {
	const stepsList = document.getElementById('stepsList');
	
	// 既存の要素をコピー
	const firstItem = stepsList.querySelector('.stepsList-item');
	const newItem = firstItem.cloneNode(true);
	
	// インデックスを更新
	const index = document.querySelectorAll('.stepsList-item').length;
	const input = newItem.querySelector('input');
	input.name = `stepsList[${index}]`;
	input.value = ''; // 新しい行なので値をクリア
	
	// 新しい要素を追加
	stepsList.appendChild(newItem);
}

// プラスボタンが押されたらステップを１つ追加
addStepBtn.addEventListener('click', () => {
  addStepItem();
});

// ********** フォーム送信・キャンセル **********
const recipeForm = document.getElementById('recipeForm');


function handleClear() {
  if (confirm('入力を取り消してよろしいですか？')) {
    // キャンセル時のリセット処理など
    recipeForm.reset();
    imagePreview.innerHTML = '';
    ingredientList.innerHTML = '';
    stepsList.innerHTML = '';
  }
}

document.addEventListener("DOMContentLoaded", function () {
    let submitButton = document.getElementById("submit-btn");
	console.log("登録");
    submitButton.addEventListener("click", function (event) {
        if (!confirm("登録しますか？")) {
            event.preventDefault(); // 送信をキャンセル
        }
    });
});


document.addEventListener("DOMContentLoaded", function () {
    let isFormDirty = false;
	
	// クリアボタンと登録ボタンのクラスを指定
	const excludeButtons = ["clear-btn", "submit-btn", "addIngredientBtn", "addStepBtn"]; 
	   
    // 入力があったらフラグを立てる
    document.querySelectorAll("input, select").forEach(element => {
        element.addEventListener("input", () => {
            isFormDirty = true;
        });
    });

    // 画面遷移しようとしたら confirm を表示
    document.querySelectorAll("a, button").forEach(element => {
        element.addEventListener("click", function (event) {
			// クリアボタンと登録ボタンは除外
			if (excludeButtons.includes(event.target.id)) {
				return;
			}
            if (isFormDirty) {
                if (!confirm("入力した内容は保存されません。よろしいですか？")) {
                    event.preventDefault(); // 画面遷移をキャンセル
                }
            }
        });
    });
});
