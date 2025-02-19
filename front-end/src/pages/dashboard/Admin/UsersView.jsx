import React from 'react'
import { MDBTable, MDBTableHead, MDBTableBody, MDBContainer } from 'mdb-react-ui-kit'

const UsersView = () => {

    const users = [
        {
            fullname: "Seydou",
            username: "Zeydia",
            email: "seydou@gmail.com",
            tel: "77777777",
            role: "ADMIN"
        }
    ]

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
            <th scope='row'>{index}</th>
            <td>{user.fullname}</td>
            <td>{user.username}</td>
            <td>{user.email}</td>
            <td>{user.tel}</td>
            <td>{user.role}</td>
          </tr>)
        })}
      </MDBTableBody>
    </MDBTable>
    </MDBContainer>
  )
}

export default UsersView