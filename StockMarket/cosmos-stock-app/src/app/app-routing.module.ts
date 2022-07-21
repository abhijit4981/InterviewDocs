import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './controller/admin/admin.component';
import { ManageonecompanyComponent } from './controller/company/manageonecompany/manageonecompany.component';
import { ManagepageComponent } from './controller/company/managepage/managepage.component';
import { DashboardComponent } from './controller/dashboard/dashboard.component';
import { AddNoteComponent } from './controller/notes/add-note/add-note.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'managecompany', component: ManagepageComponent },
  { path: 'notes', component: AddNoteComponent },
  

  {path: "updatecompany/:id", component: ManageonecompanyComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
