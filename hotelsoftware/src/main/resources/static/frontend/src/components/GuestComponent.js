import React from 'react';
import {Link} from "react-router-dom";

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
            <div className="p-16">
                <div className="p-4 border border-gray-300 mb-10">
                    <div className="w-full px-4 mb-8">
                        <div className="h-2px w-full bg-gray-400 relative">
                            <div className="absolute top-1/2 left-0 h-1 bg-blue-400 w-1/3">
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


                <form>

                    <div className="p-4 mb-2 border border-gray-300">

                        <h2 className="mb-4 font-semibold">Group/Company</h2>

                        <div className="flex row">

                            <div className="w-1/2 ">
                                <input type="text" id="gname" name="gname" className="border-2 border-gray-400 w-4/6"
                                       value={this.props.gname} onChange={this.handleGnameChange}/>
                                <label htmlFor="gname" className="block text-sm text-gray-500">Name</label>
                            </div>

                            <div className="w-1/2">
                                <input type="text" id="voucher" name="voucher"
                                       className="border-2 border-gray-400 w-2/6"
                                       value={this.props.voucher} onChange={this.handleVoucherChange}/>
                                <label htmlFor="voucher" className=" block text-sm text-gray-500">Voucher Code</label>
                            </div>

                        </div>

                    </div>


                    <div className="p-4 mb-2 border border-gray-300">
                        <h2 className="mb-4 font-semibold">Full Name</h2>

                        <div className="flex row">
                            <div className="w-1/2 ">
                                <input type="text" id="firstName" name="firstName"
                                       className="border-2 border-gray-400 w-4/6"
                                       value={this.props.firstName} onChange={this.handleFirstNameChange}/>
                                <label htmlFor="firstName" className="block text-sm text-gray-500">First Name</label>
                            </div>

                            <div className="w-1/2">
                                <input type="text" id="lastName" name="lastName"
                                       className="border-2 border-gray-400 w-4/6"
                                       value={this.props.lastName} onChange={this.handleLastNameChange}/>
                                <label htmlFor="lastName" className=" block text-sm text-gray-500">Last Name</label>
                            </div>
                        </div>

                    </div>


                    <div className="p-4 mb-2 border border-gray-300">

                        <h2 className="mb-4 font-semibold">Address</h2>

                        <div className="flex row">
                            <div className="w-1/2 ">
                                <input type="text" id="streetAdr" name="streetAdr"
                                       className="border-2 border-gray-400 w-4/6"
                                       value={this.props.streetAdr} onChange={this.handleStreetAdrChange}/>
                                <label htmlFor="streetAdr" className="block text-sm text-gray-500">Street
                                    Address</label>
                            </div>

                            <div className="w-1/2">
                                <input type="text" id="zip" name="zip" className="border-2 border-gray-400 w-2/6"
                                       value={this.props.zip} onChange={this.handleZipChange}/>
                                <label htmlFor="zip" className=" block text-sm text-gray-500">ZIP Code</label>
                            </div>
                        </div>

                        <div className="mt-4 flex row">
                            <div className="w-1/2 ">
                                <input type="text" id="city" name="city" className="border-2 border-gray-400 w-4/6"
                                       value={this.props.city} onChange={this.handleCityChange}/>
                                <label htmlFor="city" className="block text-sm text-gray-500">City</label>
                            </div>

                            <div className="w-1/2">
                                <input type="text" id="country" name="country"
                                       className="border-2 border-gray-400 w-4/6"
                                       value={this.props.country} onChange={this.handleCountryChange}/>
                                <label htmlFor="country" className=" block text-sm text-gray-500">Country</label>
                            </div>
                        </div>

                    </div>


                    <div className="p-4 border border-gray-300">

                        <div className="flex-row">
                            <div className="w-3/6 inline-block">
                                <h2 className="mb-4 font-semibold">Phone Number</h2>
                            </div>

                            <div className="w-1/3 inline-block">
                                <h2 className="mb-4 font-semibold">Email</h2>
                            </div>
                        </div>

                        <div className="flex row">
                            <div className="w-1/2">
                                <input type="text" id="phone" name="phone" className="border-2 border-gray-400 w-4/6"
                                       value={this.props.phone} onChange={this.handlePhoneChange}/>
                                <label htmlFor="phone" className="block text-sm text-gray-500">Number</label>
                            </div>

                            <div className="w-1/2">
                                <input type="text" id="email" name="email" className="border-2 border-gray-400 w-4/6"
                                       value={this.props.email} onChange={this.handleEmailChange}/>
                                <label htmlFor="email" className=" block text-sm text-gray-500">Email</label>
                            </div>
                        </div>

                    </div>


                    <div className="flex w-full justify-between mt-6">

                        <button type="button"
                                className="w-1/5 p-1 rounded-lg border-2 border-opacity-75 border-blue-50 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black"
                                onClick={this.backGuest}>
                            Back
                        </button>

                        <button type="button" className="w-1/5 p-1 rounded-lg border-2 border-opacity-75 border-blue-50 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black"
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