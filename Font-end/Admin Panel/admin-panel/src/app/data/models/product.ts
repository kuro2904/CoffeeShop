import { Image } from "./image";
import { ProductDetail } from "./product-detail";

export interface Product {
    id?: number,
    name: string,
    description: string,
    categoryId: number,
    categoryName:string,
    isActive: number,
    details: ProductDetail[],
    images: Image[]
}
