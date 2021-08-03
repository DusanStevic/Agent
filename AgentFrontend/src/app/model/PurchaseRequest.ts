export class PurchaseRequest {
  constructor(public productId: number,
              public quantity: number,
              public name: string,
              public phone: string,
              public address: string) {
  }
}
