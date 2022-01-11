import React from 'react';

class StayComponent extends React.Component {

    constructor(props) {
        super(props);

        this.state = {prices: ""};

        this.handleSingleRoomCountChange = this.handleSingleRoomCountChange.bind(this);
        this.handleDoubleRoomCountChange = this.handleDoubleRoomCountChange.bind(this);
        this.handleSuperiorRoomCountChange = this.handleSuperiorRoomCountChange.bind(this);
        this.handleCheckInDateChange = this.handleCheckInDateChange.bind(this);
        this.handleCheckOutDateChange = this.handleCheckOutDateChange.bind(this);

        this.backStay = this.backStay.bind(this);
        this.checkInputStay = this.checkInputStay.bind(this);
    }

    componentDidMount()
    {
        fetch("http://localhost:8080/rest/booking/getRoomPrices").then(res => res.json())
            .then(result => {this.setState({prices: result})})
    }

    handleSingleRoomCountChange(e)
    {
        this.props.onSingleRoomCountChange(e.target.value);

        const singleRooms = document.getElementById("single").value;
        const doubleRooms = document.getElementById("double").value;
        const superiorRooms = document.getElementById("superior").value;

        const date1 = new Date(document.getElementById("checkInDate").value);
        const date2 = new Date(document.getElementById("checkOutDate").value);

        // One day in milliseconds
        const oneDay = 1000 * 60 * 60 * 24;

        // Calculating the time difference between two dates
        const diffInTime = date2.getTime() - date1.getTime();

        // Calculating the no. of days between two dates
        const duration = Math.round(diffInTime / oneDay);

        if(isNaN(duration)){
            var price = 0;
        } else {
            var price = ((singleRooms * 40) + (doubleRooms * 60) + (superiorRooms * 100)) * duration;
        }

        document.getElementById("totalPrice").innerHTML = price;
    }

    handleDoubleRoomCountChange(e)
    {
        this.props.onDoubleRoomCountChange(e.target.value);

        const singleRooms = document.getElementById("single").value;
        const doubleRooms = document.getElementById("double").value;
        const superiorRooms = document.getElementById("superior").value;

        const date1 = new Date(document.getElementById("checkInDate").value);
        const date2 = new Date(document.getElementById("checkOutDate").value);

        // One day in milliseconds
        const oneDay = 1000 * 60 * 60 * 24;

        // Calculating the time difference between two dates
        const diffInTime = date2.getTime() - date1.getTime();

        // Calculating the no. of days between two dates
        const duration = Math.round(diffInTime / oneDay);

        if(isNaN(duration)){
            var price = 0;
        } else {
            var price = ((singleRooms * 40) + (doubleRooms * 60) + (superiorRooms * 100)) * duration;
        }

        document.getElementById("totalPrice").innerHTML = price;
    }

    handleSuperiorRoomCountChange(e)
    {
        this.props.onSuperiorRoomCountChange(e.target.value);

        const singleRooms = document.getElementById("single").value;
        const doubleRooms = document.getElementById("double").value;
        const superiorRooms = document.getElementById("superior").value;

        const date1 = new Date(document.getElementById("checkInDate").value);
        const date2 = new Date(document.getElementById("checkOutDate").value);

        // One day in milliseconds
        const oneDay = 1000 * 60 * 60 * 24;

        // Calculating the time difference between two dates
        const diffInTime = date2.getTime() - date1.getTime();

        // Calculating the no. of days between two dates
        const duration = Math.round(diffInTime / oneDay);

        if(isNaN(duration)){
            var price = 0;
        } else {
            var price = ((singleRooms * 40) + (doubleRooms * 60) + (superiorRooms * 100)) * duration;
        }

        document.getElementById("totalPrice").innerHTML = price;
    }

    handleCheckInDateChange(e)
    {
        this.props.onCheckInDateChange(e.target.value);

        const singleRooms = document.getElementById("single").value;
        const doubleRooms = document.getElementById("double").value;
        const superiorRooms = document.getElementById("superior").value;

        const date1 = new Date(document.getElementById("checkInDate").value);
        const date2 = new Date(document.getElementById("checkOutDate").value);

        // One day in milliseconds
        const oneDay = 1000 * 60 * 60 * 24;

        // Calculating the time difference between two dates
        const diffInTime = date2.getTime() - date1.getTime();

        // Calculating the no. of days between two dates
        const duration = Math.round(diffInTime / oneDay);

        if(isNaN(duration)){
            var price = 0;
        } else {
            var price = ((singleRooms * 40) + (doubleRooms * 60) + (superiorRooms * 100)) * duration;
        }

        document.getElementById("totalPrice").innerHTML = price;
    }

    handleCheckOutDateChange(e)
    {
        this.props.onCheckOutDateChange(e.target.value);

        const singleRooms = document.getElementById("single").value;
        const doubleRooms = document.getElementById("double").value;
        const superiorRooms = document.getElementById("superior").value;

        const date1 = new Date(document.getElementById("checkInDate").value);
        const date2 = new Date(document.getElementById("checkOutDate").value);

        // One day in milliseconds
        const oneDay = 1000 * 60 * 60 * 24;

        // Calculating the time difference between two dates
        const diffInTime = date2.getTime() - date1.getTime();

        // Calculating the no. of days between two dates
        const duration = Math.round(diffInTime / oneDay);

        if(isNaN(duration)){
            var price = 0;
        } else {
            var price = ((singleRooms * 40) + (doubleRooms * 60) + (superiorRooms * 100)) * duration;
        }

        document.getElementById("totalPrice").innerHTML = price;
    }

    backStay()
    {
        this.props.onBackStay();
    }

    checkInputStay()
    {
        this.props.onCheckInputStay();
    }

    render() {

        let prices = Object.values(this.state.prices);
        let singleRoomPrice = prices[0];
        let doubleRoomPrice = prices[1];
        let superiorRoomPrice = prices[2];

        console.log(singleRoomPrice);
        console.log(doubleRoomPrice);
        console.log(superiorRoomPrice);

        return (
            <React.Fragment>
                <h1 className="text-3xl mb-4 font-semibold">Rooms + Duration</h1>

                <form>

                    <div className="p-4 border border-gray-300">

                        <h2 className="mb-4 font-semibold">Category Selection</h2>

                        <div className="flex">

                            <div className="w-1/3">
                                <h2 className="mb-2 font-semibold">Single </h2>
                                <input type="number" id="single" min="0" className="border-2 border-gray-400 w-16"
                                       value={this.props.singleRoomCount} onChange={this.handleSingleRoomCountChange}/>
                                <label htmlFor="single" className="mt-2 block text-sm text-gray-500">Room Count</label>
                            </div>

                            <div className="w-1/3">
                                <h2 className="mb-2 font-semibold">Double</h2>
                                <input type="number" id="double" min="0" className="border-2 border-gray-400 w-16"
                                       value={this.props.doubleRoomCount} onChange={this.handleDoubleRoomCountChange}/>
                                <label htmlFor="double" className="mt-2 block text-sm text-gray-500">Room Count</label>
                            </div>

                            <div className="w-1/3">
                                <h2 className="mb-2 font-semibold">Superior</h2>
                                <input type="number" id="superior" min="0" className="border-2 border-gray-400 w-16"
                                       value={this.props.superiorRoomCount} onChange={this.handleSuperiorRoomCountChange}/>
                                <label htmlFor="superior" className="mt-2 block text-sm text-gray-500">Room Count</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-4 mt-2 border border-gray-300">

                        <h2 className="mb-4 font-semibold">Length of stay</h2>

                        <div className="flex row">

                            <div className="w-1/2">
                                <input type="date" id="checkInDate" className="p-2 border-2 border-gray-400 mb-2"
                                       value={this.props.checkInDate} onChange={this.handleCheckInDateChange}/>
                                <label htmlFor="checkInDate" className="block text-sm text-gray-500">Check-In Date</label>
                            </div>

                            <div className="w-1/2">
                                <input type="date" id="checkOutDate" className="p-2 border-2 border-gray-400 mb-2"
                                       value={this.props.checkOutDate} onChange={this.handleCheckOutDateChange}/>
                                <label htmlFor="checkOutDate" className="block text-sm text-gray-500">Check-Out Date</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-4 mt-4 border border-gray-300">

                        <h2 className="mb-0 text-xl font-semibold">Price for stay: <span id="totalPrice"> </span> </h2>

                    </div>

                    <div className="flex row justify-between mt-6">

                        <button type="button" className="w-1/5 p-1 rounded-lg border-2 border-opacity-75 border-blue-50 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black"
                            onClick={this.backStay}>
                            Back
                        </button>

                        <button type="button"
                                className="w-1/5 p-1 rounded-lg border-2 border-opacity-75 border-blue-50 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black"
                                onClick={this.checkInputStay}>
                            Next
                        </button>
                    </div>

                </form>
            </React.Fragment>
        );
    }
}
export default StayComponent;