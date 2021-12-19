function Calculation(){
    const singleRooms = document.getElementById("single").value;
    const doubleRooms = document.getElementById("double").value;
    const superiorRooms = document.getElementById("superior").value;

    const priceSingleRoom = document.getElementById("priceSingleRoom").value;
    const priceDoubleRoom = document.getElementById("priceDoubleRoom").value;
    const priceSuperiorRoom = document.getElementById("priceSuperiorRoom").value;

    const duration = getNumberOfDays();

    if(isNaN(duration)){
        var price = 0;
    } else {
        var price = ((singleRooms * priceSingleRoom) + (doubleRooms * priceDoubleRoom) + (superiorRooms * priceSuperiorRoom)) * duration;
    }

    document.getElementById("totalPrice").innerHTML = price;

}

function getNumberOfDays() {
    const date1 = new Date(document.getElementById("checkInDate").value);
    const date2 = new Date(document.getElementById("checkOutDate").value);

    // One day in milliseconds
    const oneDay = 1000 * 60 * 60 * 24;

    // Calculating the time difference between two dates
    const diffInTime = date2.getTime() - date1.getTime();

    // Calculating the no. of days between two dates
    const diffInDays = Math.round(diffInTime / oneDay);

    return diffInDays;
}
