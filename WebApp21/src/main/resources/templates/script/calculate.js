

fillData();

document.getElementById("coming").value = "";

function calculate(){
    let priceBuy = Number(document.getElementById("priceBuy").value);
    let priceSell = Number(document.getElementById("priceSell").value);
    let remainsLast = Number(document.getElementById("remainsLast").value);
    let coming = Number(document.getElementById("coming").value);
    let remainsCurrent = Number(document.getElementById("remainsCurrent").value);
    let sumCurrent;
    let grossProfit;

    let balance;

    sumCurrent = (remainsLast + coming - remainsCurrent) * priceSell;
    grossProfit = (remainsLast + coming - remainsCurrent) * (priceSell - priceBuy);


    document.getElementById("sumCurrent").value = sumCurrent;
    document.getElementById("notebookValue").value = sumCurrent
    document.getElementById("grossProfit").value = grossProfit;

    let notebookValue = Number(document.getElementById("notebookValue").value);
    balance = notebookValue - sumCurrent;
    document.getElementById("balance").value = balance;
}

function calculateBalance(){
    let sumCurrent = Number(document.getElementById("sumCurrent").value);
    let notebookValue = Number(document.getElementById("notebookValue").value);
    let balance = notebookValue - sumCurrent;
    document.getElementById("balance").value = balance;
}


function fillData(){
    let productId = this.document.getElementById("productId").value;
    let shiftId = this.document.getElementById("shiftId").value;

    let request = new XMLHttpRequest();
    request.open("GET", "/report-values?productId=" + productId + "&shiftId=" + shiftId, false);
    request.send(null);


        let response = request.responseText;

        let newPriceBuy = JSON.parse(response).priceBuy;
        let newPriceSell = JSON.parse(response).priceSell;
        let newRemainsLast = JSON.parse(response).remainsLast;

        document.getElementById("priceBuy").value = newPriceBuy;
        document.getElementById("priceSell").value = newPriceSell;
        document.getElementById("remainsLast").value = newRemainsLast;

    if(request.status != 200){
        document.getElementById("priceBuy").value = "";
        document.getElementById("priceSell").value = "";
        document.getElementById("remainsLast").value = "";

    }
}
