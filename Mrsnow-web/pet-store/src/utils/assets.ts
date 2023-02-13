const getAssetsFile = (name: String) => {
    return new URL('/src/assets/goods/'+name,import.meta.url).href
}
export default getAssetsFile;