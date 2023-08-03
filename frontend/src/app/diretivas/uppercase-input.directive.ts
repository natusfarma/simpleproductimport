import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[uppercase]'
})
export class UppercaseDirective {
  constructor(private el: ElementRef) {}

  @HostListener('input', ['$event.target'])
  onInput(input: HTMLInputElement) {
    const value = input.value.toUpperCase();
    input.value = value;
  }
}
