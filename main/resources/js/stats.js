google.charts.load("current", {packages: ["corechart"]});
google.charts.setOnLoadCallback(drawTransactionsPercent);
google.charts.setOnLoadCallback(drawTransactions);

function drawTransactionsPercent() {
    let res = [['Статус', '%']];

    res.push(["Выполнено", completedTransaction]);
    res.push(["В ожидании", waitingTransaction]);

    var data = google.visualization.arrayToDataTable(res);

    let options = {
        title: 'Транзакции в %',
        hAxis: {title: 'Статус транзакции'},
        vAxis: {title: 'Проценты'},
        bar: {groupWidth: "80%"},
        legend: {position: "none"}
    };

    let chart = new google.visualization.ColumnChart(document.getElementById('drawTransactionsPercent'));
    chart.draw(data, options);
}

function drawTransactions() {
    let res = [['Сумма', 'Количество']];

    //(до 1000, 1000-2500, 5000-10000, 10000-15000 и более 15000)

    res.push(["до 1000", by1000]);
    res.push(["1000-2500", with1000by2500]);
    res.push(["2500-5000", with2500by5000]);
    res.push(["5000-10000", with5000by10000]);
    res.push(["10000-15000", with10000by15000]);
    res.push(["более 15000", with15000]);

    var data = google.visualization.arrayToDataTable(res);

    let options = {
        title: 'Транзакции в $',
        hAxis: {title: 'Величина в $'},
        vAxis: {title: 'Количество'},
        bar: {groupWidth: "80%"},
        legend: {position: "none"}
    };

    let chart = new google.visualization.ColumnChart(document.getElementById('drawTransactions'));
    chart.draw(data, options);
}