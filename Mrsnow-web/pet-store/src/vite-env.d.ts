/// <reference types="vite/client" />
declare global{
    interface ResponseSuccess<T = {}> {
        code: number;
        data: T;
        message: string;
    }
}
export {}