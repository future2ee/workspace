function checkSelectAll(){
    const checkboxes = document.querySelectorAll('input[name="chk"]');
    const checked = document.querySelectorAll('input[name="chk"]:checked');
    const selectAll = document.querySelectorAll('input[name="allChk"]');

    if(checkboxes.length === checked.length){
        selectAll.checked = true;
    }else{
        selectAll.checked = false;
    }

}

function selectAll(selectAll)  {
    const checkboxes 
       = document.getElementsByName('chk');
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = selectAll.checked
    })
  }

document.getElementById("btn").addEventListener("click",function(){
    let result ="";

    for(let i of document.querySelectorAll(".check-one:checked")){
        result += i.parentElement.innerText+" ";
    }
})