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
