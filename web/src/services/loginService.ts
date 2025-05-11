import axios from "axios";
import { Login } from "../types/Login";

const API_URL = "http://localhost:8080/api/logins";

export const getAllLogins = () => axios.get<Login[]>(API_URL);
export const getLoginById = (id: number) => axios.get<Login>(`${API_URL}/${id}`);
export const createLogin = (data: Omit<Login, "id">) => axios.post<Login>(API_URL, data);
export const updateLogin = (id: number, data: Omit<Login, "id">) => axios.put<Login>(`${API_URL}/${id}`, data);
export const deleteLogin = (id: number) => axios.delete(`${API_URL}/${id}`);
