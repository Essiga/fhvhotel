import React from 'react';
import pool from "../images/pool.jpg";

class GuestComponent extends React.Component {
    constructor(props) {
        super(props);

        this.handleGnameChange = this.handleGnameChange.bind(this);
        this.handleVoucherChange = this.handleVoucherChange.bind(this);
        this.handleFirstNameChange = this.handleFirstNameChange.bind(this);
        this.handleLastNameChange = this.handleLastNameChange.bind(this);
        this.handleStreetAdrChange = this.handleStreetAdrChange.bind(this);
        this.handleZipChange = this.handleZipChange.bind(this);
        this.handleCityChange = this.handleCityChange.bind(this);
        this.handleCountryChange = this.handleCountryChange.bind(this);
        this.handlePhoneChange = this.handlePhoneChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);

        this.backGuest= this.backGuest.bind(this);
        this.checkInputGuest = this.checkInputGuest.bind(this);
    }

    handleGnameChange(e) {
        this.props.onGnameChange(e.target.value);
    }

    handleVoucherChange(e) {
        this.props.onVoucherChange(e.target.value);
    }

    handleFirstNameChange(e) {
        this.props.onFirstNameChange(e.target.value);
    }

    handleLastNameChange(e) {
        this.props.onLastNameChange(e.target.value);
    }

    handleStreetAdrChange(e) {
        this.props.onStreetAdrChange(e.target.value);
    }

    handleZipChange(e) {
        this.props.onZipChange(e.target.value);
    }

    handleCityChange(e) {
        this.props.onCityChange(e.target.value);
    }

    handleCountryChange(e) {
        this.props.onCountryChange(e.target.value);
    }

    handlePhoneChange(e) {
        this.props.onPhoneChange(e.target.value);
    }

    handleEmailChange(e) {
        this.props.onEmailChange(e.target.value);
    }

    backGuest() {
        this.props.onBackGuest();
    }

    checkInputGuest()
    {
        this.props.onCheckInputGuest();
    }

    render() {
        return (
            <div className="p-16 bg-gray-50 h-full w-full bg-no-repeat bg-cover" style={{backgroundImage: `url(${pool})`}}>

                <div className="py-2 h-1/12 mb-10 border-4 border-blue-200 rounded bg-gray-50 bg-opacity-90">
                    <div className="w-full px-4 mb-6">
                        <div className="w-full bg-gray-400 relative">
                            <div className="ml-32 absolute top-1/2 left-0 h-1 bg-blue-400 w-1/3">
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


                <form>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 bg-opacity-90">

                        <h2 className="mb-2 font-semibold tracking-wider">Group/Company</h2>

                        <div className="flex row">

                            <div className="w-1/2 ">
                                <input type="text" id="gname" name="gname" className="p-1 border-2 border-gray-400 w-4/6 mb-0.5"
                                       value={this.props.gname} onChange={this.handleGnameChange}/>
                                <label htmlFor="gname" className="block text-xs text-gray-500 tracking-tighter">NAME</label>
                            </div>

                            <div className="w-1/2">
                                <input type="text" id="voucher" name="voucher"
                                       className="p-1 border-2 border-gray-400 w-2/6 mb-0.5"
                                       value={this.props.voucher} onChange={this.handleVoucherChange}/>
                                <label htmlFor="voucher" className=" block text-xs text-gray-500 tracking-tighter">VOUCHER CODE</label>
                            </div>

                        </div>

                    </div>


                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 bg-opacity-90">
                        <h2 className="mb-2 font-semibold tracking-wider">Full Name</h2>

                        <div className="flex row">
                            <div className="w-1/2 ">
                                <input type="text" id="firstName" name="firstName" required
                                       className="p-1 border-2 border-gray-400 w-4/6 mb-0.5"
                                       value={this.props.firstName} onChange={this.handleFirstNameChange}/>
                                <label htmlFor="firstName" className="block text-xs text-gray-500 tracking-tight">FIRST NAME</label>
                            </div>

                            <div className="w-1/2">
                                <input type="text" id="lastName" name="lastName"
                                       className="p-1 border-2 border-gray-400 w-4/6 mb-0.5"
                                       value={this.props.lastName} onChange={this.handleLastNameChange}/>
                                <label htmlFor="lastName" className=" block text-xs text-gray-500 tracking-tight">LAST NAME</label>
                            </div>
                        </div>

                    </div>


                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 bg-opacity-90">

                        <h2 className="mb-2 font-semibold tracking-wider">Address</h2>

                        <div className="flex row">
                            <div className="w-1/2 ">
                                <input type="text" id="streetAdr" name="streetAdr"
                                       className="p-1 border-2 border-gray-400 w-4/6 mb-0.5"
                                       value={this.props.streetAdr} onChange={this.handleStreetAdrChange}/>
                                <label htmlFor="streetAdr" className="block text-xs text-gray-500 tracking-tight">STREET ADDRESS</label>
                            </div>

                            <div className="w-1/2">
                                <input type="text" id="zip" name="zip" className="p-1 border-2 border-gray-400 w-2/6 mb-0.5"
                                       value={this.props.zip} onChange={this.handleZipChange}/>
                                <label htmlFor="zip" className=" block text-xs text-gray-500 tracking-tight">ZIP</label>
                            </div>
                        </div>

                        <div className="mt-4 flex row">
                            <div className="w-1/2 ">
                                <input type="text" id="city" name="city" className="p-1 border-2 border-gray-400 w-4/6 mb-0.5"
                                       value={this.props.city} onChange={this.handleCityChange}/>
                                <label htmlFor="city" className="block text-xs text-gray-500 tracking-tight">CITY</label>
                            </div>

                            <div className="w-1/2">
                                <input type="text" id="country" name="country"
                                       className="p-1 border-2 border-gray-400 w-4/6 mb-0.5"
                                       value={this.props.country} onChange={this.handleCountryChange}/>
                                <label htmlFor="country" className=" block text-xs text-gray-500 tracking-tight">COUNTRY</label>
                            </div>
                        </div>

                    </div>


                    <div className="p-6 mb-6 border-4 border-blue-200 rounded bg-gray-100 bg-opacity-90">

                        <div className="flex-row">
                            <div className="w-3/6 inline-block">
                                <h2 className="mb-2 font-semibold tracking-wider">Phone Number</h2>
                            </div>

                            <div className="w-1/3 inline-block">
                                <h2 className="mb-2 font-semibold tracking-wider">Email</h2>
                            </div>
                        </div>

                        <div className="flex row">
                            <div className="w-1/2">
                                <input type="text" id="phone" name="phone" className="p-1 border-2 border-gray-400 w-4/6 mb-0.5"
                                       value={this.props.phone} onChange={this.handlePhoneChange}/>
                                <label htmlFor="phone" className="block text-xs text-gray-500 tracking-tight">NUMBER</label>
                            </div>

                            <div className="w-1/2">
                                <input type="text" id="email" name="email" className="p-1 border-2 border-gray-400 w-4/6 mb-0.5"
                                       value={this.props.email} onChange={this.handleEmailChange}/>
                                <label htmlFor="email" className=" block text-xs text-gray-500 tracking-tight">EMAIL</label>
                            </div>
                        </div>

                    </div>


                    <div className="flex w-full justify-between mt-8">

                        <button type="button"
                                className="w-1/5 p-1 rounded-lg border-2 border-opacity-75 border-blue-50 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black opacity-95"
                                onClick={this.backGuest}>
                            Back
                        </button>

                        <button type="button" className="w-1/5 p-1 rounded-lg border-2 border-opacity-75 border-blue-50 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black opacity-95"
                                onClick={this.checkInputGuest}>
                            Next
                        </button>

                    </div>

                </form>
            </div>
        );
    }
}
export default GuestComponent;