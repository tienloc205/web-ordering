import { useQuery } from '@tanstack/react-query';
import { getMenu } from '../services/dishService';

export const useMenu = () => {
    return useQuery({
        queryKey: ['menu'],
        queryFn: getMenu,
        staleTime: 1000 * 60 * 5, // Cache 5 phút
    });
};