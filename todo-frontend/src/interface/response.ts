type Links = Record<string, Record<'href', string>> & {
  self: { href: string };
};

type HasLinks<T> = T & {
  _links: Links;
};

export type Response<T> = HasLinks<T>;

export type ListResponse<T> = HasLinks<{
  _embedded: Response<T>;
}>;
