import React from 'react';
import doubleRoom from "../images/doubleRoom.jpg"


class DoubleRoomComponent extends React.Component {

    render() {
        return (
            <React.Fragment>
                <div className="grid grid-cols-2 gap-4 mt-5">
                   <div>
                       <img src={doubleRoom} className=""/>
                   </div>
                   <div>
                       <div className="text-2xl font-bold">Double Room</div>
                       <div className="text-xl">Stylish but with modern rooms - on 23 square meters.
                           The double rooms have high living comfort and in each room hangs a picture of a Vorarlberg artist.</div>

                       <div className="mt-5 text-xl font-bold">Room details:</div>

                       <ul className="list-disc ml-8">
                           <li className="text-xl">balcony</li>
                           <li className="text-xl">bathtub / WC</li>
                           <li className="text-xl">flat screen tv</li>
                           <li className="text-xl">WLAN</li>
                           <li className="text-xl">hairdryer</li>
                       </ul>

                   </div>

                </div>
            </React.Fragment>
        );
    }

}
export default DoubleRoomComponent;