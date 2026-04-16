import { cn } from "@/lib/utils";

interface CategoryBarProps {
    categories: string[];
    activeCategory: string;
    onCategoryClick: (category: string) => void;
}

export const CategoryBar = ({ categories, activeCategory, onCategoryClick }: CategoryBarProps) => {
    return (
        <div className="sticky top-14 z-10 bg-white border-b overflow-x-auto no-scrollbar flex whitespace-nowrap px-4 py-2 gap-6">
            {categories.map((cat) => (
                    <button
                        key={cat}
                onClick={() => onCategoryClick(cat)}
    className={cn(
        "pb-1 text-sm font-medium transition-colors border-b-2 border-transparent",
        activeCategory === cat ? "border-orange-500 text-orange-600" : "text-gray-500"
)}
>
    {cat}
    </button>
))}
    </div>
);
};