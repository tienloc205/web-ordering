import { DishControllerApi, type DishResponseDTO } from '@/api/generated';
import axiosInstance from '../config/axiosInstance';
import {BASE_PATH} from "../api/generated/base.ts";

const dishApi = new DishControllerApi(undefined, BASE_PATH, axiosInstance);

export const getMenu = async (): Promise<Record<string, DishResponseDTO[]>> => {
    const response = await dishApi.getMenu();
    return response.data;
};