import { useState } from 'react'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import Login from './pages/login/Login'
import Signup from './pages/signup/Signup'
import Home from './pages/home/Home'
import User from './pages/dashboard/User/User'
import Admin from './pages/dashboard/Admin/Admin'
import { MyInfos as MyInfosAdmin } from './pages/dashboard/Admin/MyInfos'
import { MyInfos as MyInfosUser } from './pages/dashboard/User/MyInfos'
import UsersView from './pages/dashboard/Admin/UsersView'
import Layout from './pages/dashboard/Layout'

function App() {


  return (
    <BrowserRouter>
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />

        
        <Route path="/" element={<Layout />}>
          <Route path="admin">
            <Route index element={<Admin />} />
            <Route path="myInfos" element={<MyInfosAdmin />} />
            <Route path="dashboard" element={<UsersView />} />
          </Route>
          <Route path="user">
            <Route index element={<User />} />
            <Route path="myInfos" element={<MyInfosUser />} />
          </Route>
        </Route>

        <Route path="*" element={<Home />} />


      </Routes>
    </BrowserRouter>
  )
}

export default App
