import React from 'react';
import {Link} from "react-router-dom";

class StayComponent extends React.Component {

    constructor(props) {
        super(props);

        this.state = {prices: ""};

        this.handleSingleRoomCountChange = this.handleSingleRoomCountChange.bind(this);
        this.handleDoubleRoomCountChange = this.handleDoubleRoomCountChange.bind(this);
        this.handleSuperiorRoomCountChange = this.handleSuperiorRoomCountChange.bind(this);
        this.handleCheckInDateChange = this.handleCheckInDateChange.bind(this);
        this.handleCheckOutDateChange = this.handleCheckOutDateChange.bind(this);

        this.checkInputStay = this.checkInputStay.bind(this);
    }

    componentDidMount() {
        fetch("http://localhost:8080/rest/booking/getRoomPrices").then(res => res.json())
            .then(result => {
                this.setState({prices: Object.values(result)})
            })
    }

    calculateRoomPrices() {
        let prices = this.state.prices;
        let singleRoomPrice = prices[0];
        let doubleRoomPrice = prices[1];
        let superiorRoomPrice = prices[2];

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

        let price;

        if (isNaN(duration)) {
            price = 0;
        } else {
            price = ((singleRooms * singleRoomPrice) + (doubleRooms * doubleRoomPrice) + (superiorRooms * superiorRoomPrice)) * duration;
        }

        document.getElementById("totalPrice").innerHTML = price + "€";
    }

    handleSingleRoomCountChange(e) {
        this.props.onSingleRoomCountChange(e.target.value);
        this.calculateRoomPrices();
    }

    handleDoubleRoomCountChange(e) {
        this.props.onDoubleRoomCountChange(e.target.value);
        this.calculateRoomPrices();
    }

    handleSuperiorRoomCountChange(e) {
        this.props.onSuperiorRoomCountChange(e.target.value);
        this.calculateRoomPrices();
    }

    handleCheckInDateChange(e) {
        this.props.onCheckInDateChange(e.target.value);
        this.calculateRoomPrices();
    }

    handleCheckOutDateChange(e) {
        this.props.onCheckOutDateChange(e.target.value);
        this.calculateRoomPrices();
    }

    checkInputStay() {
        this.props.onCheckInputStay();
    }

    render() {

        return (
            <React.Fragment>
                <div className="p-4 border border-gray-300 mb-10">
                    <div className="w-full px-4 mb-8">
                        <div className="h-2px w-full bg-gray-400 relative">
                            <div className="absolute top-1/2 left-0 h-1 bg-blue-400 w-0">
                                <div className="w-3 h-3 bg-blue-800 rounded-full absolute right-0 top-1/2 transform translate-x-1/2 -translate-y-1/2"></div>
                            </div>
                        </div>
                        <div className="mt-3 pt-2 relative hidden sm:block">
                            <div className="absolute left-0"><span
                                className="inline-block transform -translate-x-1/2 text-sm font-medium text-blue-400">Stay</span>
                            </div>
                            <div className="absolute left-1/3"><span
                                className="inline-block transform -translate-x-1/2 text-sm font-medium text-blue-400">Personal Data</span>
                            </div>
                            <div className="absolute left-2/3"><span
                                className="inline-block transform -translate-x-1/2 text-sm font-medium text-blue-400">Summary</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div>


                </div>

                <form>
                    <div className="p-4 border border-gray-300">
                        <div className="grid grid-cols-2 gap-8">

                            <div className="border-r-2">
                                <h2 className="mb-4 font-semibold">Length of stay</h2>
                                <div className="flex row">
                                    <div className="w-1/2">
                                        <input type="date" id="checkInDate"
                                               className="p-2 border-2 border-gray-400 mb-2"
                                               value={this.props.checkInDate} onChange={this.handleCheckInDateChange}/>
                                        <label htmlFor="checkInDate" className="block text-sm text-gray-500">Check-In
                                            Date</label>
                                    </div>

                                    <div className="w-1/2">
                                        <input type="date" id="checkOutDate"
                                               className="p-2 border-2 border-gray-400 mb-2"
                                               value={this.props.checkOutDate}
                                               onChange={this.handleCheckOutDateChange}/>
                                        <label htmlFor="checkOutDate" className="block text-sm text-gray-500">Check-Out
                                            Date</label>
                                    </div>
                                </div>
                            </div>
                            <div>


                                <div className="flex">

                                    <div className="w-1/3">
                                        <h2 className="mb-4 font-semibold">Single </h2>
                                        <input type="number" id="single" min="0"
                                               className="border-2 p-1.5 border-gray-400 w-16"
                                               value={this.props.singleRoomCount}
                                               onChange={this.handleSingleRoomCountChange}/>
                                        <label htmlFor="single" className="mt-2 block text-sm text-gray-500">Room
                                            Count</label>
                                    </div>

                                    <div className="w-1/3">
                                        <h2 className="mb-4 font-semibold">Double</h2>
                                        <input type="number" id="double" min="0"
                                               className="border-2 p-1.5 border-gray-400 w-16"
                                               value={this.props.doubleRoomCount}
                                               onChange={this.handleDoubleRoomCountChange}/>
                                        <label htmlFor="double" className="mt-2 block text-sm text-gray-500">Room
                                            Count</label>
                                    </div>

                                    <div className="w-1/3">
                                        <h2 className="mb-4 font-semibold">Superior</h2>
                                        <input type="number" id="superior" min="0"
                                               className="border-2 p-1.5 border-gray-400 w-16"
                                               value={this.props.superiorRoomCount}
                                               onChange={this.handleSuperiorRoomCountChange}/>
                                        <label htmlFor="superior" className="mt-2 block text-sm text-gray-500">Room
                                            Count</label>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>

                    <div className="p-4 mt-4 border border-gray-300">

                        <h2 className="mb-0 text-xl font-semibold">Price for stay: <span id="totalPrice">0€</span></h2>

                    </div>

                    <div className="flex row justify-between mt-6">

                        <Link to="/"
                              className="block w-1/5 p-1 rounded-lg border-2 border-opacity-75 border-blue-50 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black">
                            <button type="button"> Back </button>
                        </Link>

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