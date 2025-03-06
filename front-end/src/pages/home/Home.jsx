import { useEffect } from 'react'
import { getCookie } from '../../utils/Cookies'
import { useNavigate } from 'react-router-dom';

const Home = () => {
    const token = getCookie('LOGIN_INFO');
    const history = useNavigate();

    useEffect(() => {
        if(token){
            history("/")
        } 
    })


  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="border rounded-lg p-4" style={{width: '500px', height: '400px'}}>
                <h2 className="mb-4 text-center">Bienvenue sur la page d&apos;acceuil</h2>
                <p className="text-center">Hello, Je suis Zeydia!<br/> 
                Bienvenue sur mon projet LOGGIN PROJECT site web fait avec <span className="text-primary">Reactjs</span> et <span className='text-success'>Spring Boot Spring Security</span>. </p>
                <p className="text-center">Veuillez vous connecter ou créer un compte</p>
                <div className="text-center">
                    <a href="/login">
                        <button type="button" className="btn btn-primary m-3">
                        Se connecter
                        </button>
                    </a>
                    <a href="/signup" className="text-white text-decoration-none">
                        <button type="button" className="btn btn-primary m-3" >
                            S&apos;inscrire
                        </button>
                    </a>
                </div>
            </div>
        </div>
  )
}

export default Home