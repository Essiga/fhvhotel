import React from 'react';
import {Link} from "react-router-dom";
import snow from '../images/snow.jpg'
import SingleRoomComponent from "./SingleRoomComponent";
import DoubleRoomComponent from "./DoubleRoomComponent";
import SuperiorRoomComponent from "./SuperiorRoomComponent";

class StayComponent extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            prices: "",
            rooms: "",
            singleRoomSelected: false,
            doubleRoomSelected: false,
            superiorRoomSelected: false
        };


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
            });
    }
    updateMaxRooms() {
        const stayDates =
            {
                checkInDate: this.props.checkInDate,
                checkOutDate: this.props.checkOutDate
            };

        fetch("http://localhost:8080/rest/booking/getTotalRoom",
            {
                method: 'POST',
                headers: {'content-type': 'application/json'},
                body: JSON.stringify(stayDates)
            }).then(res => res.json()).then(result => { this.setState({rooms: Object.values(result)})})
    }

    updateMaxRoomsCheckIn(e) {
        const stayDates =
            {
                checkInDate: e.target.value,
                checkOutDate: this.props.checkOutDate
            };

        fetch("http://localhost:8080/rest/booking/getTotalRoom",
            {
                method: 'POST',
                headers: {'content-type': 'application/json'},
                body: JSON.stringify(stayDates)
            }).then(res => res.json()).then(result => { this.setState({rooms: Object.values(result)})})
    }

    updateMaxRoomsCheckOut(e) {
        const stayDates =
            {
                checkInDate: this.props.checkInDate,
                checkOutDate: e.target.value
            };

        fetch("http://localhost:8080/rest/booking/getTotalRoom",
            {
                method: 'POST',
                headers: {'content-type': 'application/json'},
                body: JSON.stringify(stayDates)
            }).then(res => res.json()).then(result => { this.setState({rooms: Object.values(result)})})
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

        if (this.props.checkInDate != null && this.props.checkInDate != "" && //find me: call function only when both checkInDate and checkOutDate are not null
            this.props.checkOutDate != null && this.props.checkOutDate != "")
            this.updateMaxRooms();

        if (e.target.value > 0) {
            this.setState({singleRoomSelected: true})

        } else {
            this.setState({singleRoomSelected: false})
        }
    }

    handleDoubleRoomCountChange(e) {
        this.props.onDoubleRoomCountChange(e.target.value);
        this.calculateRoomPrices();

        if (this.props.checkInDate != null && this.props.checkInDate != "" &&
            this.props.checkOutDate != null && this.props.checkOutDate != "")
            this.updateMaxRooms();

       if (e.target.value > 0) {
            this.setState({doubleRoomSelected: true})
        } else {
            this.setState({doubleRoomSelected: false})
        }
    }

    handleSuperiorRoomCountChange(e) {
        this.props.onSuperiorRoomCountChange(e.target.value);
        this.calculateRoomPrices();

        if (this.props.checkInDate != null && this.props.checkInDate != "" &&
            this.props.checkOutDate != null && this.props.checkOutDate != "")
            this.updateMaxRooms();

       if (e.target.value > 0) {
            this.setState({superiorRoomSelected : true})
       } else {
            this.setState({superiorRoomSelected : false})
       }
    }

    handleCheckInDateChange(e) {
        this.props.onCheckInDateChange(e.target.value);
        this.calculateRoomPrices();

        if (this.props.checkInDate != null && this.props.checkInDate != "" &&
            this.props.checkOutDate != null && this.props.checkOutDate != ""){
            this.updateMaxRoomsCheckIn(e);
        }



    }

    handleCheckOutDateChange(e) {
        this.props.onCheckOutDateChange(e.target.value);
        this.calculateRoomPrices();

        if (this.props.checkInDate != null && this.props.checkInDate != "" &&
            this.props.checkOutDate != null && this.props.checkOutDate != ""){
            this.updateMaxRoomsCheckOut(e);
        }



    }

    checkInputStay() {
        this.props.onCheckInputStay();
    }

    render() {

        return (

            <div className="overflow-scroll p-16 bg-gray-50 h-full w-full bg-no-repeat bg-cover" style={{backgroundImage: `url(${snow})`}}>

                <div className="py-2 h-1/12 mb-10 border-4 border-blue-200 rounded bg-gray-50 opacity-90">
                    <div className="w-full px-4 mb-6">
                        <div className="w-full bg-gray-400 relative">
                            <div className="ml-32 absolute top-1/2 left-0 h-1 bg-blue-400 w-0">
                                <div className="w-3 h-3 bg-blue-800 rounded-full absolute right-0 top-1/2 transform translate-x-1/2 -translate-y-1/2"></div>
                            </div>
                        </div>
                        <div className="ml-32 mt-3 pt-2 relative hidden sm:block">
                            <div className="absolute left-0"><span
                                className="inline-block transform -translate-x-1/2 text-m font-semibold text-blue-400 tracking-wider">Stay</span>
                            </div>
                            <div className="absolute left-1/3 pl-8"><span
                                className="inline-block transform -translate-x-1/2 text-m font-semibold text-blue-400 tracking-wider">Personal Data</span>
                            </div>
                            <div className="absolute left-2/3 pl-20"><span
                                className="inline-block transform -translate-x-1/2 text-m font-semibold text-blue-400 tracking-wider">Summary</span>
                            </div>
                        </div>
                    </div>
                </div>

                <form className="h-full w-full">
                    <div className=" p-16 border-4 border-blue-200 rounded bg-gray-50 opacity-95">
                        <div className="grid grid-cols-2 gap-8">

                            <div className="border-r-2">
                                <h2 className="mb-2 font-semibold tracking-wider">Length of Stay</h2>
                                <div className="flex row">
                                    <div className="w-1/2">
                                        <input type="date" id="checkInDate"
                                               className="p-2 border-2 border-gray-400 mb-0.5"
                                               value={this.props.checkInDate} onChange={this.handleCheckInDateChange}/>
                                        <label htmlFor="checkInDate" className="block text-xs text-gray-500 tracking-tight">CHECK-IN DATE</label>
                                    </div>

                                    <div className="w-1/2">
                                        <input type="date" id="checkOutDate"
                                               className="p-2 border-2 border-gray-400 mb-0.5"
                                               value={this.props.checkOutDate}
                                               onChange={this.handleCheckOutDateChange}/>
                                        <label htmlFor="checkOutDate" className="block text-xs text-gray-500 tracking-tight">CHECK-OUT DATE</label>
                                    </div>
                                </div>
                            </div>
                            <div>


                                <div className="flex">

                                    <div className="pl-24 w-1/3">
                                        <h2 className="mb-2 font-semibold tracking-wider">Single</h2>
                                        <input type="number" id="single" min="0" max={this.state.rooms[0]}  //find me: set max
                                               className="border-2 p-1.5 border-gray-400 w-16 mb-0.5"
                                               value={this.props.singleRoomCount}
                                               onChange={this.handleSingleRoomCountChange}/>
                                        <label htmlFor="single" className="block text-xs text-gray-500 tracking-tight">ROOMS {this.state.rooms[0]}</label>
                                    </div>

                                    <div className="pl-12 w-1/3">
                                        <h2 className="mb-2 font-semibold tracking-wider">Double</h2>
                                        <input type="number" id="double" min="0" max={this.state.rooms[1]}
                                               className="border-2 p-1.5 border-gray-400 w-16 mb-0.5"
                                               value={this.props.doubleRoomCount}
                                               onChange={this.handleDoubleRoomCountChange}/>
                                        <label htmlFor="double" className="block text-xs text-gray-500 tracking-tight">ROOMS {this.state.rooms[1]}</label>
                                    </div>

                                    <div className="w-1/3">
                                        <h2 className="mb-2 font-semibold tracking-wider">Superior</h2>
                                        <input type="number" id="superior" min="0" max={this.state.rooms[2]}
                                               className="border-2 p-1.5 border-gray-400 w-16 mb-0.5"
                                               value={this.props.superiorRoomCount}
                                               onChange={this.handleSuperiorRoomCountChange}/>
                                        <label htmlFor="superior" className="block text-xs text-gray-500 tracking-tight">ROOMS {this.state.rooms[2]}</label>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>

                    <div className="p-1 mt-12 border-4 border-blue-200 rounded bg-gray-100 opacity-90">

                        <h2 className="text-center text-xl font-semibold">Price for Stay: <span id="totalPrice">0€</span></h2>

                    </div>

                    <div className="flex justify-between mt-8">

                        <Link to="/"
                              className="block w-1/5 p-1 rounded-lg border-2 border-blue-200 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black opacity-95">
                            <button type="button"> Back </button>
                        </Link>

                        <button type="button"
                                className="w-1/5 p-1 rounded-lg border-2 border-blue-200 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black opacity-95"
                                onClick={this.checkInputStay}>
                            Next
                        </button>
                    </div>

                    {this.state.singleRoomSelected | this.state.doubleRoomSelected | this.state.superiorRoomSelected  &&<div className="p-1 mt-12 border-4 border-blue-200 rounded bg-gray-100 opacity-90">
                        {this.state.singleRoomSelected && <SingleRoomComponent /> }
                        {this.state.doubleRoomSelected && <DoubleRoomComponent /> }
                        {this.state.superiorRoomSelected && <SuperiorRoomComponent /> }
                    </div>}




                </form>
            </div>
        );
    }
}

export default StayComponent;