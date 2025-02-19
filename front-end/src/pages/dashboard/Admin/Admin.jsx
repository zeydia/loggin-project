import React from 'react'
import { Link } from 'react-router-dom'
import Logout from '../../logout/Logout'

const Admin = () => {
  return (
    <div className="d-flex justify-content-center align-items-center">
      <div className="border rounded-lg p-4" style={{ width: '500px', height: '400px' }}>
        <h2 className="mb-4 text-center">Welcome to the Admin Dashboard</h2>
        <p className="text-center">Hello, I am Elijah21!<br />
          Welcome to my loggin website write in <span className="">Reactjs</span> and <span>Spring Boot</span>. </p>
        <p className="text-center">You can view your informations or view all users</p>
        <div className="text-center">
          <Link to={"/admin/myInfos"}>
            <button type="button" className="btn btn-primary m-3">
              My Informations
            </button>
          </Link>
          <Link to={"/admin/dashboard"} className="text-white text-decoration-none">
            <button type="button" className="btn btn-primary m-3" >
              View All Users
            </button>
          </Link>
        </div>
        <div className="text-center">
          <Logout />

        </div>
      </div>
    </div>
  )
}

export default Admin