import type {DishResponseDTO} from "@/api/generated";
import { Button } from "@/components/ui/button";
import { Plus } from "lucide-react";

interface DishCardProps {
    dish: DishResponseDTO;
}

export const DishCard = ({ dish }: DishCardProps) => {
    return (
        <div className="flex gap-3 py-4 border-b last:border-0">
            {/* Thumbnail */}
            <div className="relative h-24 w-24 flex-none">
                <img
                    src={dish.imageUrl || "/placeholder-food.png"}
                    alt={dish.name}
                    className="h-full w-full rounded-lg object-cover"
                />
            </div>

            {/* Content */}
            <div className="flex flex-col justify-between flex-1">
                <div>
                    <h3 className="font-bold text-gray-800 leading-tight">{dish.name}</h3>
                    <p className="text-xs text-gray-500 line-clamp-2 mt-1">
                        {dish.description}
                    </p>
                </div>

                <div className="flex justify-between items-center mt-2">
          <span className="text-orange-600 font-bold">
            {dish.price?.toLocaleString()}đ
          </span>
                    <Button size="icon" className="h-8 w-8 rounded-full bg-orange-500 hover:bg-orange-600">
                        <Plus className="h-5 w-5" />
                    </Button>
                </div>
            </div>
        </div>
    );
};