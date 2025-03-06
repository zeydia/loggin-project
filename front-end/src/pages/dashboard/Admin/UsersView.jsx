import React, { useState, useEffect } from 'react'
import { MDBTable, MDBTableHead, MDBTableBody, MDBContainer } from 'mdb-react-ui-kit'
import { getCookie } from '../../../utils/Cookies';
import axios from 'axios';

const UsersView = () => {
  const api = import.meta.env.VITE_API;
  const token = getCookie("LOGIN_INFO");
  const [users, setUsers] = useState([]);

  
  useEffect(() => {
    axios.get(api + "/admin/users", {
      headers:{
        Authorization: `Bearer ${token}`
      }
    })
    .then((response) => setUsers([...users, ...response.data]))
    .catch((error) => console.error(error))
  },[])

  return (
    <MDBContainer>
        <MDBTable>
      <MDBTableHead light>
        <tr>
          <th scope='col'>#</th>
          <th scope='col'>FullName</th>
          <th scope='col'>Username</th>
          <th scope='col'>Email</th>
          <th scope='col'>Tel</th>
          <th scope='col'>Role</th>
        </tr>
      </MDBTableHead>
      <MDBTableBody>
        {users.map((user,index) => {
        return(<tr key={index}>
            <th scope='row'>{user.userId}</th>
            <td>{user.fullname}</td>
            <td>{user.username}</td>
            <td>{user.email}</td>
            <td>{user.mobile}</td>
            <td>{user.role}</td>
          </tr>)
        })}
      </MDBTableBody>
    </MDBTable>
    </MDBContainer>
  )
}

export default UsersView