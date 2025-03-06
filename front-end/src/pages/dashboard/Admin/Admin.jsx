import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import Logout from '../../logout/Logout'
import axios from 'axios'
import { getCookie } from '../../../utils/Cookies'

const Admin = () => {
  const [username, setUsername] = useState();
  const api = import.meta.env.VITE_API;
  const token = getCookie('LOGIN_INFO');

  useEffect(() => {
    axios.get(api + "/user", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        setUsername(response.data.username);

      })
      .catch(error => console.log(error))
  }, [])


  return (
    <div className="d-flex justify-content-center align-items-center">
      <div className="border rounded-lg pt-4 mt-3" style={{ width: '700px', height: '500px' }}>
      <h2 className="mt-5 mb-4 text-center">Bienvenue <span className="text-primary">{username}</span> sur le dashboard Admin</h2>
        <p className="text-center">Bonjour, Je suis Elijah21!<br />
          Bienvenue sur mon projet LOGGIN PROJECT site web fait avec <span className="text-primary">Reactjs</span> et <span className='text-success'>Spring Boot Spring Security</span>. </p>
        <p className="text-center">Consultez vos informations ou deconnectez-vous</p>
        <div className="text-center">
          <Link to={"/admin/myInfos"}>
            <button type="button" className="btn btn-primary m-3" >
              Mes Informations
            </button>
          </Link>
          <Link to={"/admin/dashboard"} className="text-white text-decoration-none">
            <button type="button" className="btn btn-primary m-3" >
              Voir tous les utilisateurs
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