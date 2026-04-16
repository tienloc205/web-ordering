import {useEffect, useState} from "react";
import { Search } from "lucide-react";
import { Input } from "@/components/ui/input";
import { useMenu } from "@/hooks/useMenu";

import { Skeleton } from "@/components/ui/skeleton";
import {CategoryBar} from "@/features/menu/CategoryBar.tsx";
import {DishCard} from "@/features/menu/DishCard.tsx";

export const MenuPage = () => {
    const { data: menuData, isLoading } = useMenu();
    const [activeCategory, setActiveCategory] = useState<string>("");

    const categories = menuData ? Object.keys(menuData) : [];

    // Tự động chọn category đầu tiên khi dữ liệu tải xong lần đầu
    useEffect(() => {
        if (categories.length > 0 && !activeCategory) {
            setActiveCategory(categories[0]);
        }
    }, [categories, activeCategory]);

    // Hàm xử lý khi click category (không cuộn nữa mà chỉ đổi State)
    const handleCategoryChange = (category: string) => {
        setActiveCategory(category);
    };

    if (isLoading) return <MenuLoadingSkeleton />;

    // Lấy danh sách món ăn thuộc category đang được chọn
    const currentDishes = activeCategory ? menuData?.[activeCategory] || [] : [];

    return (
        <div className="min-h-screen bg-white pb-20">
            {/* Header & Search */}
            <div className="sticky top-0 z-20 bg-white px-4 py-2 space-y-2">
                <div className="relative">
                    <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-gray-400" />
                    <Input
                        placeholder="Bạn muốn tìm món gì?"
                        className="pl-10 bg-gray-100 border-none rounded-full h-10"
                    />
                </div>
            </div>

            {/* Category Navigation - Thanh ngang */}
            <CategoryBar
                categories={categories}
                activeCategory={activeCategory}
                onCategoryClick={handleCategoryChange}
            />

            {/* Menu List - Chỉ hiển thị Category đang chọn */}
            <div className="px-4 mt-4">
                {activeCategory && (
                    <section>
                        <h2 className="text-lg font-bold text-gray-900 mb-4 border-l-4 border-orange-500 pl-2">
                            {activeCategory}
                        </h2>

                        {currentDishes.length > 0 ? (
                            <div className="flex flex-col">
                                {currentDishes.map((dish) => (
                                    <DishCard key={dish.id} dish={dish} />
                                ))}
                            </div>
                        ) : (
                            <div className="text-center py-10 text-gray-400">
                                Danh mục này hiện chưa có món nào.
                            </div>
                        )}
                    </section>
                )}
            </div>
        </div>
    );
};

const MenuLoadingSkeleton = () => (
    <div className="p-4 space-y-4">
        <Skeleton className="h-10 w-full rounded-full" />
        <Skeleton className="h-8 w-full" />
            {[1, 2, 3, 4].map(i => (
        <div key={i} className="flex gap-4">
            <Skeleton className="h-24 w-24 rounded-lg" />
            <div className="flex-1 space-y-2">
            <Skeleton className="h-4 w-3/4" />
            <Skeleton className="h-4 w-full" />
            <Skeleton className="h-4 w-1/4 mt-4" />
        </div>
    </div>
))}
</div>
);