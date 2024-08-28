export function Fetch(url: string, method: string, purpose: string): Promise<Response>;
export function Fetch(url: string, method: string, purpose: string, body: any): Promise<Response>;

export function Fetch(url: string, method: string, purpose: string, body?: any): Promise<Response> {

    let metadata: RequestInit = {
        method: `${method}`,
        headers: {'Content-Type': 'application/json'},
    }
    if (body) metadata.body = JSON.stringify(body)

    let result = fetch(`${url}`, metadata);
    result.catch(error => console.error(`${purpose}  !!!  ${error}`));
    return result
}

