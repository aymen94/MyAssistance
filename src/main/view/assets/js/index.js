var data = [
    [0, 4, "Buona Notte"],
    [5, 11, "Buon Giorno"],
    [12, 17, "Buon Pomeriggio"],
    [18, 24, "Buona Sera"]
],
    hr = new Date().getHours();

$(document).ready(
    function () {
        for (var i = 0; i < data.length; i++) {
            if (hr >= data[i][0] && hr <= data[i][1]) {
                $('#greet').replaceWith(data[i][2])
            }
        }
    }
)