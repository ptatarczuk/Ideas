import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Layout from './components/Layout/Layout';
import UsersList from './pages/UsersList/UsersList';
import Login from './pages/Login/Login';
import './App.css';
import { Threads } from './components/Threads/Threads';
import { NotFound } from './components/NotFound/NotFound';
import { ThreadPage } from './components/Threads/ThreadPage';
function App() {

  return (
    <BrowserRouter>

<Routes>
      <Route path="/" element={<Layout />} >
      <Route index element={<Threads />} />
      <Route path="users" element={<UsersList />} />
      <Route path="login" element={<Login />} />
      <Route path="threads" element={<Threads />} />
      <Route path="thread/:id" element={<ThreadPage />} />
      <Route path="*" element={<NotFound />} />
      </Route>
    </Routes>
  </BrowserRouter>
  );
}

export default App;
