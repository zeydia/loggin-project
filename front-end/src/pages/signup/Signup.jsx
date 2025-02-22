import axios from 'axios'
import { MDBBtn, MDBContainer, MDBInput, MDBInputGroup, MDBNavbar } from 'mdb-react-ui-kit'
import React, { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'

const Signup = () => {

  const [state, setState] = useState({
    fullname: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
    mobile: '',
    roles: 'ROLE_USER',
    error: ''
  })
  const history = useNavigate()

  const handleChange = (e) => {
    setState({ ...state, [e.target.id]: e.target.value })
  }

  const handleSubmit = async () => {

    try {
      const { fullname, username, email, password, confirmPassword, mobile, roles } = state
      const role = { roleName: roles }

      if (fullname == '' || username == '' || email == '' || password == '' || confirmPassword == '' || mobile == '' || role == '') {
        setState({ ...state, error: 'Veuillez remplir tous les champs' })
      }
      else if (password !== confirmPassword) {
        setState({ ...state, error: "La confirmation de mot de passe n'est pas correcte." })
      }
      else if ((mobile.search(/[^\d]/g)) != -1){
        setState({ ...state, error: 'Il y a un caractère non numerique dans le numéro de téléphone.' })
      }
      else {
        await axios
        .post("http://localhost:8080/api/signup",{ fullname, username, email, password, mobile, role })

      if(state.roles == "ROLE_USER") history("/user") 
      else history("/admin");
    
      }
                                
    }
    catch (err) {
      setState({ ...state, error: "Le nom d'utilisateur ou l'adresse mail est déjà utilisé."})
    }

  }


  return (
    <div>

      <div className='mb-4'>
        <MDBNavbar>
          <MDBContainer className='m-2'>
            <Link to={"/"}>
              VERS L'ACCEUIL
            </Link>
          </MDBContainer>
        </MDBNavbar>
      </div>

      <div className="d-flex justify-content-center align-items-center">
        <div className="card p-4" style={{ width: "600px", height: "auto" }}>
          <MDBContainer className="p-3">
            {/* SIGN UP TITLE */}
            <h2 className="text-center mb-4">Créer un compte</h2>

            {/* ERROR */}
            {state.error && <p className="text-danger text-center mb-4">{state.error}</p>}

            {/* FULLNAME */}
            <MDBInput wrapperClass='mb-3' id='fullname' label='Nom complet' type='text' value={state.fullname}
              onChange={handleChange}>
            </MDBInput>

            {/* USERNAME */}
            <MDBInput wrapperClass='mb-3' id='username' label="Nom d'utilisateur" type='text' value={state.username}
              onChange={handleChange}>
            </MDBInput>

            {/* EMAIL */}
            <MDBInput wrapperClass='mb-3' id='email' label="Adresse mail" type='text' value={state.email}
              onChange={handleChange}>
            </MDBInput>

            {/* PASSWORD */}
            <MDBInput wrapperClass='mb-3' id='password' label='Mot de passe' type='password' value={state.password}
              onChange={handleChange}>
            </MDBInput>

            {/* CONFIRM PASSWORD */}
            <MDBInput wrapperClass='mb-3' id='confirmPassword' label='Confirmation du mot de passe' type='password' value={state.confirmPassword}
              onChange={handleChange}>
            </MDBInput>

            {/* MOBILE */}
            <MDBInputGroup textBefore='+221'>
              <MDBInput wrapperClass='mb-3' id='mobile' label='Téléphone' type='tel' value={state.mobile}
                onChange={handleChange}>
              </MDBInput>
            </MDBInputGroup>


            {/* ROLE */}
            <div className='flex'>
              <label>Role</label>
              <select className="form-select mb-4" id='roles' value={state.roles}
                onChange={handleChange}>
                <option value="ROLE_USER">Utilisateur</option>
                <option value="ROLE_ADMIN">Admininistrateur</option>
              </select>
            </div>

            {/* SUBMIT */}
            <MDBBtn className='mb-3'
              style={{ height: '40px', width: '100%' }}
              onClick={handleSubmit}>Créer un compte
            </MDBBtn>

            <div className="text-center">
              <p>Déjà inscrit? <a href="/login">Connectez-vous ici</a></p>
            </div>

          </MDBContainer>
        </div>
      </div>
    </div>
  )
}

export default Signup