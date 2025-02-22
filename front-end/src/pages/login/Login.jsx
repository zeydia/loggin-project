import React from 'react'
import { MDBContainer, MDBInput, MDBBtn, MDBNavbar } from 'mdb-react-ui-kit'
import axios from 'axios'
import { Link, useNavigate } from 'react-router-dom'

const Login = () => {

  const [state, setState] = React.useState({
    username: '',
    password: '',
    error: ''
  })

  const history = useNavigate();

  const handleSubmit = async () => {
    const { username, password } = state

    try {
      if (state.username === '' || state.password === '') {
        setState({ ...state, error: 'Please fill in all fields' })
      }


      const response1 = await axios.post('http://localhost:8080/api/login', { username, password });
      const response = await axios.get('http://localhost:8080/api/user');
      console.log(response)
      //console.log(response)
      //history("/admin")

    } catch (error) {
      console.error('Login failed:', error.response ? error.response.data : error.message);
      setState({ ...state, error: "Nom d'utilisateur ou mot de passe incorrect"});
    }

  }


  return (
    <div>
      <div className='mb-6'>
      <MDBNavbar>
      <MDBContainer className='m-2'>
        <Link to={"/"}>
          VERS L'ACCEUIL
        </Link>
      </MDBContainer>
    </MDBNavbar>
      </div>
      <div className="d-flex justify-content-center align-items-center">
        <div className="card p-4" style={{ width: "500px", height: "auto" }}>
          <MDBContainer className="p-3">
            {/* LOGIN TITLE */}
            <h2 className="text-center mb-4">Se connecter</h2>

            {/* USERNAME */}
            <MDBInput wrapperClass='mb-3' id='username' label="Nom d'utilisateur" type='text' value={state.username}
              onChange={(e) => {
                setState({ ...state, [e.target.id]: e.target.value })
              }}>
            </MDBInput>

            {/* PASSWORD */}
            <MDBInput wrapperClass='mb-3' id='password' label="Mot de passe" type='password' value={state.password}
              onChange={(e) => {
                setState({ ...state, [e.target.id]: e.target.value })
              }}>
            </MDBInput>

            {/* ERROR */}
            {state.error && <p className="text-danger text-center mb-4">{state.error}</p>}

            {/* SUBMIT */}
            <MDBBtn className='mb-3'
              style={{ height: '40px', width: '100%' }}
              onClick={handleSubmit}>Se connecter
            </MDBBtn>

            <div className="text-center">
              <p>Vous n'avez pas de compte? <Link to={"/signup"} >Créer un compte</Link></p>
            </div>

          </MDBContainer>
        </div>
      </div>
    </div>
  )
}

export default Login