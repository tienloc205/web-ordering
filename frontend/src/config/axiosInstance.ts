import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 15000,
});

// Chặn để thêm Table ID vào mọi yêu cầu
axiosInstance.interceptors.request.use((config) => {
    const tableId = localStorage.getItem('current_table_id');
    if (tableId) {
        config.headers['X-Table-Id'] = tableId;
    }
    return config;
});

// Chặn để xử lý lỗi chung
axiosInstance.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 500) {
            alert("Hệ thống phía nhà hàng đang gặp sự cố!");
        }
        return Promise.reject(error);
    }
);

export default axiosInstance;