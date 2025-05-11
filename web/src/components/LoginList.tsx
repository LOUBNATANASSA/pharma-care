import React, { useEffect, useState } from 'react';
import { Login } from '../types/Login';
import {
  getAllLogins,
  createLogin,
  updateLogin,
  deleteLogin
} from '../services/loginService';
import LoginForm from './LoginForm';

const LoginList: React.FC = () => {
  const [logins, setLogins] = useState<Login[]>([]);
  const [editingLogin, setEditingLogin] = useState<Login | null>(null);

  const fetchLogins = async () => {
    const response = await getAllLogins();
    setLogins(response.data);
  };

  useEffect(() => {
    fetchLogins();
  }, []);

  const handleCreate = async (data: Omit<Login, "id">) => {
    await createLogin(data);
    fetchLogins();
  };

  const handleUpdate = async (data: Omit<Login, "id">) => {
    if (editingLogin) {
      await updateLogin(editingLogin.id, data);
      setEditingLogin(null);
      fetchLogins();
    }
  };

  const handleDelete = async (id: number) => {
    await deleteLogin(id);
    fetchLogins();
  };

  return (
    <div>
      <h2>Gestion des Logins</h2>
      {editingLogin ? (
        <LoginForm initialData={editingLogin} onSubmit={handleUpdate} isEdit />
      ) : (
        <LoginForm onSubmit={handleCreate} />
      )}

      <ul>
        {logins.map(login => (
          <li key={login.id}>
            {login.username} - {login.email} - {login.type}
            <button onClick={() => setEditingLogin(login)}>Modifier</button>
            <button onClick={() => handleDelete(login.id)}>Supprimer</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default LoginList;
