import React from 'react'

class RoomComponent extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = {rooms: []};
    }

    componentDidMount()
    {
        fetch("http://localhost:8080/rest/booking/getAllRooms").then(res => res.json())
            .then(result => {this.setState({rooms: result})})
    }

    render()
    {
        return (
            <div className="m-4 h-6/12 overflow-scroll">

                <table className="w-full table-fixed bg-white" id="allRooms">
                    <tr className="bg-blue-100 text-xl">
                        <th className="w-1/5 border-2 py-2 border-blue-200">Room Number</th>
                        <th className="w-1/5 border-2 py-2 border-blue-200">Room Category</th>
                        <th className="w-1/5 border-2 py-2 border-blue-200">Room Status</th>
                    </tr>

                     {this.state.rooms.map(room => (
                         <tr key = {room.roomNumber} className="text-center m-4">
                            <td className="border py-2 px-4">{room.roomNumber}</td>
                            <td className="border py-2 px-4">{room.roomCategory}</td>
                            <td className="border py-2 px-4">{room.roomStatus}</td>
                         </tr>
                     ))}
                </table>
            </div>
        );
    }
}

export default RoomComponent;