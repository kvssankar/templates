import { Controller, Get, UseGuards } from "@nestjs/common";
import { AuthGuard } from "@nestjs/passport";
import { User } from "./interface/user.interface";
import { AuthUser } from "./user.decorator";

@Controller("user")
export class UserController {
  @Get("/onlyauth")
  @UseGuards(AuthGuard("jwt"))
  async hiddenInformation(@AuthUser() authPaylaod: User) {
    return authPaylaod;
  }
}
