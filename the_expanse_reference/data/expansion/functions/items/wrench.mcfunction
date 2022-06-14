# change terradome mode
execute as @e[type=armor_stand,tag=shield_generator,distance=..0.4,limit=1,sort=nearest] run scoreboard players add @s mode_switcher 1
execute as @e[type=armor_stand,tag=shield_generator,distance=..0.4,limit=1,sort=nearest] if score @s mode_switcher matches 2.. run scoreboard players set @s mode_switcher 0
# demount vehicles
execute if entity @s[tag=rocket] run function expansion:vehicles/rocket/demount
execute if entity @s[tag=moon_buggy] run function expansion:vehicles/buggy/demount
execute if entity @s[tag=lunar_module] run function expansion:vehicles/lunar_module/demount
execute if entity @s[tag=return_capsule] run function expansion:vehicles/return_capsule/demount
execute if entity @s[tag=exp_spaceship] run function expansion:vehicles/spaceship/demount
execute if entity @s[tag=module_bottom] run function expansion:vehicles/lunar_module/demount_bottom
