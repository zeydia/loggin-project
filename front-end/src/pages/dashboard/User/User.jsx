import React from 'react'
import { Link } from 'react-router-dom'
import Logout from '../../logout/Logout'

const User = () => {
  return (
    <div className="d-flex justify-content-center align-items-center">
      <div className="border rounded-lg p-4" style={{ width: '500px', height: '400px' }}>
        <h2 className="mb-4 text-center">Welcome to the User Dashboard</h2>
        <p className="text-center">Hello, I am Elijah21!<br />
          Welcome to my loggin website write in <span className="">Reactjs</span> and <span>Spring Boot</span>. </p>
        <p className="text-center">You can view your informations or logout</p>
        
        <div className="text-center">
          <Link to={"/user/myInfos"}>
            <button type="button" className="btn btn-primary m-3">
              My Informations
            </button>
          </Link>
          
          <Logout />

        </div>
      </div>
    </div>
  )
}

export default User