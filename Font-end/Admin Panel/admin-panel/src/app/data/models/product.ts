import { ProductDetail } from './product-detail';

export interface Product {
  id?: number;
  name: string;
  description: string;
  categoryId: number;
  categoryName?: string;
  isActive: number;
  rate?: number;
  rateSum?: number;
  details: ProductDetail[];
  images: string[];
}
