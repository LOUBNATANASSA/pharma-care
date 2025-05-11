import React, { useState } from 'react';
import { Login } from '../types/Login';

interface Props {
  initialData?: Partial<Login>;
  onSubmit: (data: Omit<Login, "id">) => void;
  isEdit?: boolean;
}

const LoginForm: React.FC<Props> = ({ initialData = {}, onSubmit, isEdit = false }) => {
  const [formData, setFormData] = useState<Omit<Login, "id">>({
    username: initialData.username || '',
    email: initialData.email || '',
    password: initialData.password || '',
    type: initialData.type || ''
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({...formData, [e.target.name]: e.target.value});
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(formData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <input name="username" value={formData.username} onChange={handleChange} placeholder="Username" required />
      <input name="email" type="email" value={formData.email} onChange={handleChange} placeholder="Email" required />
      <input name="password" type="password" value={formData.password} onChange={handleChange} placeholder="Password" required />
      <input name="type" value={formData.type} onChange={handleChange} placeholder="Type" required />
      <button type="submit">{isEdit ? 'Update' : 'Create'} Login</button>
    </form>
  );
};

export default LoginForm;
