function calculate(){
    let priceBuy = Number(document.getElementById("priceBuy").value);
    let priceSell = Number(document.getElementById("priceSell").value);
    let remainsLast = Number(document.getElementById("remainsLast").value);
    let coming = Number(document.getElementById("coming").value);
    let remainsCurrent = Number(document.getElementById("remainsCurrent").value);
    let sumCurrent;
    let grossProfit;
    let notebookValue = Number(document.getElementById("notebookValue").value);
    let balance;

    sumCurrent = (remainsLast + coming - remainsCurrent) * priceSell;
    grossProfit = (remainsLast + coming - remainsCurrent) * (priceSell - priceBuy);
    balance = notebookValue - grossProfit;

    document.getElementById("sumCurrent").value = sumCurrent;
    document.getElementById("grossProfit").value = grossProfit;
    document.getElementById("balance").value = balance;
    document.getElementById("notebookValue").value = grossProfit
}

function calculateBalance(){
    let grossProfit = Number(document.getElementById("grossProfit").value);
    let notebookValue = Number(document.getElementById("notebookValue").value);
    let balance = notebookValue - grossProfit;
    document.getElementById("balance").value = balance;
}
