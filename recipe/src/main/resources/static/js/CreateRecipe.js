
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

// 材料用の行を追加する関数
function addIngredientItem() {
  const div = document.createElement('div');
  div.className = 'ingredient-item';
  // "・"の部分を span で表現
  const bullet = document.createElement('span');
  bullet.textContent = '・';
  const input = document.createElement('input');
  input.type = 'text';
  input.name = 'ingredients[]';
  input.placeholder = '材料を入力';
  div.appendChild(bullet);
  div.appendChild(input);
  ingredientList.appendChild(div);
}

// 初期表示として１つ作る
addIngredientItem();

// プラスボタンが押されたら材料を１行追加
addIngredientBtn.addEventListener('click', () => {
  addIngredientItem();
});

// ********** 作り方の動的追加 **********
const stepsList = document.getElementById('stepsList');
const addStepBtn = document.getElementById('addStepBtn');

let stepCount = 0;

function addStepItem() {
  stepCount++;
  const div = document.createElement('div');
  div.className = 'step-item';
  // "1." の部分を span で表現
  const number = document.createElement('span');
  number.textContent = stepCount + '．';
  const input = document.createElement('input');
  input.type = 'text';
  input.name = 'steps[]';
  input.placeholder = '手順を入力';
  div.appendChild(number);
  div.appendChild(input);
  stepsList.appendChild(div);
}

// 初期表示として１ステップ作る
addStepItem();

// プラスボタンが押されたらステップを１つ追加
addStepBtn.addEventListener('click', () => {
  addStepItem();
});

// ********** フォーム送信・キャンセル **********
const recipeForm = document.getElementById('recipeForm');

recipeForm.addEventListener('submit', (e) => {
  e.preventDefault();
  // 実際のアプリケーションではここでフォーム内容を取得して送信するなどの処理を行う
  alert('レシピが登録されました！（デモ用）');
  recipeForm.reset();
  imagePreview.innerHTML = '';
  // ステップ数や材料リストもリセットして再初期化
  ingredientList.innerHTML = '';
  stepsList.innerHTML = '';
  stepCount = 0;
  addIngredientItem();
  addStepItem();
});

function handleClear() {
  if (confirm('入力を取り消してよろしいですか？')) {
    // キャンセル時のリセット処理など
    recipeForm.reset();
    imagePreview.innerHTML = '';
    ingredientList.innerHTML = '';
    stepsList.innerHTML = '';
    stepCount = 0;
    addIngredientItem();
    addStepItem();
  }
}
