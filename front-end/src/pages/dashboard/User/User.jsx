import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { Link } from 'react-router-dom'
import Logout from '../../logout/Logout'
import { getCookie } from '../../../utils/Cookies';

const User = () => {
  const [username, setUsername] = useState();
  const api = import.meta.env.VITE_API;
  const token = getCookie('LOGIN_INFO');

  useEffect(() => {
    axios.get(api + "/user", {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    .then((response) => setUsername(response.data.username))
  },[])


  return (
    <div className="d-flex justify-content-center align-items-center">
      <div className="border rounded-lg p-4" style={{ width: '500px', height: '400px' }}>
        <h2 className="mb-4 text-center">Bienvenue <span className="text-primary">{username}</span> sur le dashboard de l'utilisateur</h2>
        <p className="text-center">Bonjour, Je suis Elijah21!<br />
          Bienvenue sur mon projet LOGGIN PROJECT site web fait avec <span className="text-primary">Reactjs</span> et <span className='text-success'>Spring Boot Spring Security</span>. </p>
        <p className="text-center">Consultez vos informations ou deconnectez-vous</p>
        
        <div className="text-center">
          <Link to={"/user/myInfos"}>
            <button type="button" className="btn btn-primary m-3">
              Mes Informations
            </button>
          </Link>
          
          <Logout />

        </div>
      </div>
    </div>
  )
}

export default User